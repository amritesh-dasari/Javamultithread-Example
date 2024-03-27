package org.example;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class Main {
    public static void main(String[] args) throws IOException {

        try {
            PDDocument document = PDDocument.load(new File("D:/AG.pdf"));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] words = text.split("\\W+");

            SingleThread ST = new SingleThread(words);
            MultiThread MT = new MultiThread(words);

            MT.start();
            ST.start();

            ST.join();
            MT.join();
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}

class SingleThread extends Thread {
    private String[] words;
    public SingleThread(String[] words){
        this.words=words;
    }
    public void run()
    {
        try {
            System.out.println("Single Thread Starting...");
            LocalDateTime singlestart = LocalDateTime.now();
            System.out.println("Thread " + Thread.currentThread().threadId() + " is running");
            Utils ut = new Utils();
            ut.AverageWordLength(words,"ST");
            ut.ShortestWord(words,"ST");
            ut.LongestWord(words,"ST");
            ut.MostCommonWord(words,"ST");
            LocalDateTime singlestop = LocalDateTime.now();
//            Duration duration = Duration.between(singlestart, singlestop);
            System.out.println("SINGLE THREAD: Time taken is "+ ChronoUnit.MILLIS.between(singlestart, singlestop));

        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}
class MultiThread extends Thread {
    private String[] words;
    public MultiThread(String[] words){
        this.words=words;
    }
    public void run()
    {
        try {
            System.out.println("Multi Thread Starting...");
            LocalDateTime multistart = LocalDateTime.now();
            System.out.println("Thread " + Thread.currentThread().threadId() + " is running");
            AWL awl = new AWL(words);
            SW sw = new SW(words);
            LW lw = new LW(words);
            MCW mcw = new MCW(words);

            awl.start();
            sw.start();
            lw.start();
            mcw.start();

            awl.join();
            sw.join();
            lw.join();
            mcw.join();

            LocalDateTime multistop = LocalDateTime.now();
            Duration duration = Duration.between(multistart, multistop);
            System.out.println("MULTI THREAD: Time taken is "+ChronoUnit.MILLIS.between(multistart, multistop));

        }
        catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}

class AWL extends Thread{
    private String[] words;
    public AWL(String[] words){
        this.words=words;
    }
    public void run() {
        try {
            Utils ut = new Utils();
            ut.AverageWordLength(words,"MT");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class SW extends Thread{
    private String[] words;
    public SW(String[] words){
        this.words=words;
    }
    public void run() {
        try {
            Utils ut = new Utils();
            ut.ShortestWord(words,"MT");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class LW extends Thread{
    private String[] words;
    public LW(String[] words){
        this.words=words;
    }
    public void run() {
        try {
            Utils ut = new Utils();
            ut.LongestWord(words,"MT");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

class MCW extends Thread{
    private String[] words;
    public MCW(String[] words){
        this.words=words;
    }
    public void run() {
        try {
            Utils ut = new Utils();
            ut.MostCommonWord(words,"MT");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}