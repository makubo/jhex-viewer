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
public class BinaryData {
    
    private List<HexDataLine> hexTextData;
    private byte[] binaryData;
    private int cols = 16;
    private int group = 2;

    public List<HexDataLine> getHexTextData() {
        return hexTextData;
    }

    public byte[] getBinaryData() {
        return binaryData;
    }

    public void setBinaryData(byte[] binaryData) {
        this.binaryData = binaryData;
        
        hexTextData = new ArrayList<HexDataLine>();
        
        StringBuilder byteString = new StringBuilder();
        
        HexDataLine hexLine = null;
        for (int i = 0; i < binaryData.length; i++) {
            
            // Start new line
            if (i % cols == 0) {
                hexLine = new HexDataLine();
                hexTextData.add(hexLine);
                hexLine.setIndex(i);
            }

            hexLine.appendByte(binaryData[i]);
            
            int byteInt = binaryData[i] & 0xFF;
            if (byteInt < 32 || byteInt == 255 || (byteInt > 126 && byteInt < 161)) {
                byteString.append(".");
            } else {
                byteString.append((char) byteInt);
            }
            
            if (i % cols == cols - 1) {
                hexLine.setAsciiString(byteString.toString());
                byteString = new StringBuilder();
            } else if ( i == binaryData.length - 1){
                int number = cols - (binaryData.length % cols);
                number = number * 2 + number/2;
                hexLine.setGap(number);
                hexLine.setAsciiString(byteString.toString());
                // Not needed. That is the last iteration
                //byteString = new StringBuilder();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HexDataLine line : hexTextData){
            sb.append(line);
            if (line != hexTextData.get(hexTextData.size() - 1)){
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    
}
