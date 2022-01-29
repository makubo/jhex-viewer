/*
 * The MIT License
 *
 * Copyright 2022 Maksim Bobylev.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
    private final Integer bytesInLine;
    private final Integer groupBytesBy;

    public HexDataLine(int bytesInLine, int groupBytesBy) {
        hexString = new ArrayList<>();
        this.bytesInLine = bytesInLine;
        this.groupBytesBy = groupBytesBy;
    }

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

    public void appendByte(byte b) {
        hexString.add(String.format("%02X", b & 0xFF));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("%08X", index)).append(": ");

        for (int i = 0; i < hexString.size(); i++) {
            if (i > 0 && i % groupBytesBy == groupBytesBy - 1 || groupBytesBy == 1) {
                sb.append(hexString.get(i)).append(" ");
            } else {
                sb.append(hexString.get(i));
            }
        }
        
        if (bytesInLine % groupBytesBy != 0) {
            sb.append(" ");
        }

        int number = 0;

        if (hexString.size() < bytesInLine) {
            number = bytesInLine - (hexString.size() % bytesInLine);
            number = number * 2 + number / groupBytesBy;
        }
        for (int i = 0; i < number; i++) {
            sb.append(" ");
        }
        sb.append(asciiString);
        return sb.toString();
    }
}
