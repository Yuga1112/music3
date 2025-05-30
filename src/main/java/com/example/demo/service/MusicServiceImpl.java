package com.example.demo.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.example.demo.config.SpotifyConfig;

import com.example.demo.dto.MusicDTO;
import com.example.demo.entity.Music;
import com.example.demo.entity.Playlist;
import com.example.demo.repository.MusicRepository;
import com.example.demo.repository.PlaylistRepository;

@Service
public class MusicServiceImpl implements MusicService {

	@Autowired
	SpotifyConfig config;
	
	@Autowired
	PlaylistRepository playRepository;
	
	@Autowired
	MusicRepository musicRepository;

	public MusicServiceImpl(WebClient webClient) {
		this.webClient = webClient;
	}

	WebClient webClient;

	//토큰 발급
	public String getAccessToken() {
		String clientId = config.clientId;
		String clientSecret = config.clientSecret;
		String auth = clientId + ":" + clientSecret;
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

		HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder().uri(new URI("https://accounts.spotify.com/api/token"))
					.header("Authorization", "Basic " + encodedAuth)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials")).build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpClient client = HttpClient.newHttpClient();
		HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (response.statusCode() != 200) {
			throw new RuntimeException("Spotify 토큰 발급 실패: " + response.body());
		}

		JSONObject json = new JSONObject(response.body());
		return json.getString("access_token");
	}

	// 재생목록 출력
	@Override
	public List<MusicDTO> getPlaylist(String playlistId) {
	    String token = getAccessToken();

	    HttpRequest request = null;
		try {
			request = HttpRequest.newBuilder()
			        .uri(new URI("https://api.spotify.com/v1/playlists/" + playlistId))
			        .header("Authorization", "Bearer " + token)
			        .GET()
			        .build();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    HttpClient client = HttpClient.newHttpClient();
	    HttpResponse<String> response = null;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    JSONObject json = new JSONObject(response.body());
	    JSONArray items = json.getJSONObject("tracks").getJSONArray("items");

	    // 플레이리스트가 DB에 없으면 저장
	    Playlist playlist = playRepository.findByPlaylistId(playlistId)
	            .orElseGet(() -> playRepository.save(
	                Playlist.builder()
	                    .playlistId(playlistId)
	                    .build()
	            ));

	    List<MusicDTO> musicList = new ArrayList<>();

	    for (int i = 0; i < items.length(); i++) {
	        JSONObject trackItem = items.getJSONObject(i).getJSONObject("track");

	        String name = trackItem.getString("name");
	        String artistName = trackItem.getJSONArray("artists").getJSONObject(0).getString("name");
	        String durationMs = String.valueOf(trackItem.getInt("duration_ms"));
	        String spotifyUrl = trackItem.getJSONObject("external_urls").getString("spotify");
	        String albumImageUrl = trackItem.getJSONObject("album").getJSONArray("images").getJSONObject(1).getString("url");

	        Music musicEntity = Music.builder()
	        	    .name(name)
	        	    .artist(artistName)
	        	    .durationMs(durationMs)
	        	    .spotifyUrl(spotifyUrl)
	        	    .albumImageUrl(albumImageUrl)
	        	    .playlist(playlist) // 연관된 플레이리스트 설정
	        	    .build();

	        musicRepository.save(musicEntity); // 저장

	        // 출력용 DTO
	        musicList.add(MusicDTO.builder()
	            .name(name)
	            .artist(artistName)
	            .build());
	    }

	    return musicList;
	    }



	// 상세출력
	@Override
	public MusicDTO getTrackFromPlaylist(String playlistId, int index) throws Exception {
	    String token = getAccessToken();

	    HttpRequest request = HttpRequest.newBuilder()
	        .uri(new URI("https://api.spotify.com/v1/playlists/" + playlistId))
	        .header("Authorization", "Bearer " + token).GET().build();

	    HttpClient client = HttpClient.newHttpClient();
	    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

	    JSONObject trackItem = new JSONObject(response.body())
	        .getJSONObject("tracks")
	        .getJSONArray("items")
	        .getJSONObject(index)
	        .getJSONObject("track");

	    String name = trackItem.getString("name");
	    String artistName = trackItem.getJSONArray("artists").getJSONObject(0).getString("name");

	    // 🟢 duration 포맷 직접 처리
	    String durationFormatted = String.format("%02d:%02d", 
	        trackItem.getInt("duration_ms") / 60000, 
	        (trackItem.getInt("duration_ms") % 60000) / 1000
	    );

	    String spotifyUrl = trackItem.getJSONObject("external_urls").getString("spotify");
	    String albumImageUrl = trackItem.getJSONObject("album").getJSONArray("images").getJSONObject(1).getString("url");

	    return MusicDTO.builder()
	        .name(name)
	        .artist(artistName)
	        .durationMs(durationFormatted) // 그대로 DTO에 담기
	        .spotifyUrl(spotifyUrl)
	        .albumImageUrl(albumImageUrl)
	        .build();
	}


	//음악검색
	@Override
	public String search(String query)
			throws Exception {

		String accessToken = getAccessToken();

		 return webClient.get()
			        .uri(uriBuilder -> {
			            UriBuilder builder = uriBuilder
			                .path("/v1/search")
			                .queryParam("q", query);

			            return builder.build();
			        })
			        .headers(headers -> headers.setBearerAuth(accessToken))
			        .retrieve()
			        .bodyToMono(String.class)
			        .block();  // 여기서 block()으로 동기화
			}
	}


