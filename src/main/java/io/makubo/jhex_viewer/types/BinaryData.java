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

        hexTextData = new ArrayList<>();

        StringBuilder byteString = new StringBuilder();

        HexDataLine hexLine = null;
        for (int i = 0; i < binaryData.length; i++) {

            // Start new line
            if (i % cols == 0) {
                hexLine = new HexDataLine(cols, group);
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

            hexLine.setAsciiString(byteString.toString());
            if (i % cols == cols - 1) {
                byteString = new StringBuilder();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (HexDataLine line : hexTextData) {
            sb.append(line);
            if (line != hexTextData.get(hexTextData.size() - 1)) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

}
