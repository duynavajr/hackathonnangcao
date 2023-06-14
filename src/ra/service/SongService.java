package ra.service;

import ra.model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SongService {
    private List<Song> songs;

    public SongService() {
        songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public void updateSong(Song song) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSongId().equals(song.getSongId())) {
                songs.set(i, song);
                break;
            }
        }
    }

    public void deleteSong(String songId) {
        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i).getSongId().equals(songId)) {
                songs.remove(i);
                break;
            }
        }
    }

    public List<Song> searchSongsByName(String keyword) {
        List<Song> searchResults = new ArrayList<>();
        for (Song song : songs) {
            if (song.getSongName().toLowerCase().contains(keyword.toLowerCase())) {
                searchResults.add(song);
            }
        }
        return searchResults;
    }

    public List<Song> getSongsByNameAscending() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        Collections.sort(sortedSongs, Comparator.comparing(Song::getSongName));
        return sortedSongs;
    }

    public List<Song> getNewestSongs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        Collections.sort(sortedSongs, Comparator.comparing(Song::getCreatedDate).reversed());
        return sortedSongs.stream().limit(10).collect(Collectors.toList());
    }



    public Song findSongById(String songId) {
        for (Song song : songs) {
            if (song.getSongId().equals(songId)) {
                return song;
            }
        }
        return null;
    }

    public List<Song> getAllSongs() {
        return songs;
    }
}