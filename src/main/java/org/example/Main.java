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
            SingleThread ST = new SingleThread();
            MultiThread MT = new MultiThread();

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
    public void run()
    {
        try {
            System.out.println("Single Thread Starting...");
            LocalDateTime singlestart = LocalDateTime.now();
            System.out.println("Thread " + Thread.currentThread().threadId() + " is running");
            PDDocument document = PDDocument.load(new File("D:/LW.pdf"));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] words = text.split("\\W+");
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
    public void run()
    {
        try {
            System.out.println("Multi Thread Starting...");
            LocalDateTime multistart = LocalDateTime.now();
            System.out.println("Thread " + Thread.currentThread().threadId() + " is running");
            AWL awl = new AWL();
            SW sw = new SW();
            LW lw = new LW();
            MCW mcw = new MCW();

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
    public void run() {
        try {
            PDDocument document = PDDocument.load(new File("D:/LW.pdf"));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] words = text.split("\\W+");
            Utils ut = new Utils();
            ut.AverageWordLength(words,"MT");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class SW extends Thread{
    public void run() {
        try {
            PDDocument document = PDDocument.load(new File("D:/LW.pdf"));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] words = text.split("\\W+");
            Utils ut = new Utils();
            ut.ShortestWord(words,"MT");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class LW extends Thread{
    public void run() {
        try {
            PDDocument document = PDDocument.load(new File("D:/LW.pdf"));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] words = text.split("\\W+");
            Utils ut = new Utils();
            ut.LongestWord(words,"MT");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class MCW extends Thread{
    public void run() {
        try {
            PDDocument document = PDDocument.load(new File("D:/LW.pdf"));
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] words = text.split("\\W+");
            Utils ut = new Utils();
            ut.MostCommonWord(words,"MT");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}