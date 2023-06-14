package ra.controller;

import ra.model.Singer;
import ra.model.Song;
import ra.service.SongService;
import ra.service.SingerService;

import java.util.List;
import java.util.Scanner;

public class SongController {
    private SongService songService;
    private SingerService singerService;

    public SongController(SongService songService) {
        this.songService = this.songService;
        this.singerService = singerService;
    }



    public void addSong() {
        Song song = new Song();
        song.inputData(singerService);

        if (song.getSinger() == null) {
            System.out.println("Không tìm thấy bài hát nào. Vui lòng thêm một bài hát trước..");
            Singer newSinger = new Singer();  // Tạo một đối tượng Singer mới
            newSinger.inputData();  // Nhập thông tin cho đối tượng Singer mới
            singerService.addSinger(newSinger);  // Truyền đối tượng Singer mới vào phương thức addSinger
            song.setSinger(newSinger);
        }

        songService.addSong(song); // Thêm bài hát vào danh sách bài hát
        System.out.println("Thêm bài hát thành công.");
    }

    public void displayAllSongs() {
        List<Song> songList = Song.getSongList();
        if (songList.isEmpty()) {
            System.out.println("Danh sách tất cả bài hát trống.");
        } else {
            System.out.println("Danh sách tất cả bài hát:");
            for (Song song : songList) {
                song.displayData();
                System.out.println("------------------------");
            }
        }
    }

    public void updateSong() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập ID bài hát muốn cập nhật: ");
        String songId = scanner.nextLine();

        Song song = songService.findSongById(songId);
        if (song != null) {
            song.inputData(singerService);
            songService.updateSong(song);
            System.out.println("Cập nhật bài hát thành công.");
        } else {
            System.out.println("Không tìm thấy bài hát.");
        }
    }

    public void deleteSong() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập id bài hát muốn xóa: ");
        String songId = scanner.nextLine();

        Song song = songService.findSongById(songId);
        if (song != null) {
            songService.deleteSong(songId);
            System.out.println("Xóa bài hát thành công.");
        } else {
            System.out.println("Không tìm tháy bài hát.");
        }
    }

    public void searchSongsByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tên bài hát: ");
        String keyword = scanner.nextLine();

        List<Song> searchResults = songService.searchSongsByName(keyword);
        if (searchResults.isEmpty()) {
            System.out.println("Không tìm thấy bài hát nào có tên chứa từ khóa: " + keyword);
        } else {
            System.out.println("Kết quả tìm kiếm:");
            for (Song song : searchResults) {
                song.displayData();
                System.out.println("***************************");
            }
        }
    }

    public void displaySongsByNameAscending() {
        List<Song> sortedSongs = songService.getSongsByNameAscending();
        if (sortedSongs.isEmpty()) {
            System.out.println("Danh sách bài hát trống.");
        } else {
            System.out.println("Danh sách bài hát theo thứ tự tên tăng dần:");
            for (Song song : sortedSongs) {
                song.displayData();
                System.out.println("***************************");
            }
        }
    }

    public void displayNewestSongs() {
        List<Song> newestSongs = songService.getNewestSongs();
        if (newestSongs.isEmpty()) {
            System.out.println("Danh sách bài hát trống.");
        } else {
            System.out.println("10 bài hát được đăng mới nhất:");
            for (Song song : newestSongs) {
                song.displayData();
                System.out.println("***************************");
            }
        }
    }
}
