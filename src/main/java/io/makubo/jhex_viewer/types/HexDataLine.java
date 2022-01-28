/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.makubo.jhex_viewer.types;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maxon
 */
public class HexDataLine {

    private Integer index;
    private List<String> hexString;
    private String asciiString;
    private int gap = 0;
    private static final int cols = 16;
    private static final int groups = 4;

    public HexDataLine() {
        hexString = new ArrayList<String>();
    }

//    public HexDataLine(int index, List<String> hexString, String asciiString) {
//        this.index = index;
//        this.hexString = hexString;
//        this.asciiString = asciiString;
//    }
//
//    public HexDataLine(int index, List<String> hexString, String asciiString, int gap) {
//        this.index = index;
//        this.hexString = hexString;
//        this.asciiString = asciiString;
//        this.gap = gap;
//    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<String> getHexString() {
        return hexString;
    }

    public void setHexString(List<String> hexString) {
        this.hexString = hexString;
    }

    public String getAsciiString() {
        return asciiString;
    }

    public void setAsciiString(String asciiString) {
        this.asciiString = asciiString;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public void appendByte(byte b) {
        hexString.add(String.format("%02X", b & 0xFF));
    }

    @Override
    public String toString() {
        return toString(cols, groups);
    }

    public String toString(int columnNumber, int groupsNumber) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%08X", index)).append(": ");

        for (int i = 0; i < hexString.size(); i++) {
            if (i > 0 && i % groupsNumber != 0) {
                sb.append(hexString.get(i)).append(" ");
            } else {
                sb.append(hexString.get(i));
            }
        }
        
        int number = 0;
        
        if ( hexString.size() < columnNumber){
            number = columnNumber - (hexString.size() % columnNumber);
                number = number * 2 + number/groupsNumber;
        }
        for (int i = 0; i < number; i++) {
            sb.append(" ");
        }
        sb.append(asciiString);
        return sb.toString();
    }
}
