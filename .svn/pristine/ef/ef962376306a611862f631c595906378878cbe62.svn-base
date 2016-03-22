/*
 * Copyright (c) 2002-2014 Gargoyle Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gargoylesoftware.htmlunit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;

/**
 * Wrapper for content downloaded from a remote server.
 *
 * @version $Revision: 8931 $
 * @author Marc Guillemot
 * @author Ronald Brill
 */
public interface DownloadedContent extends Serializable {
    /**
     * Implementation keeping content in memory.
     */
    static class InMemory implements DownloadedContent {
        private byte[] bytes_;

        public InMemory(final byte[] byteArray) {
            if (byteArray == null) {
                bytes_ = ArrayUtils.EMPTY_BYTE_ARRAY;
            }
            else {
                bytes_ = byteArray;
            }
        }

        public InputStream getInputStream() {
            return new ByteArrayInputStream(bytes_);
        }

        //dyyr created @20150507
        public void setContent(String s){
            if(s!=null){
                try {
                    bytes_ = s.getBytes("UTF-8");
                }catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        }
        public void cleanUp() {
            // nothing to do
        }

        public boolean isEmpty() {
            return bytes_.length == 0;
        }
    }

    /**
     * Implementation keeping content on the file system.
     */
    static class OnFile implements DownloadedContent {
        private final File file_;
        private boolean temporary_;

        /**
         * @param file the file
         * @param temporary if true, the file will be deleted when cleanUp() is called.
         */
        public OnFile(final File file, final boolean temporary) {
            file_ = file;
            temporary_ = temporary;
        }

        //dyyr created @20150507
        public void setContent(String s) {
            if(s!=null){
                try {
                    FileUtils.writeStringToFile(file_, s);
                }
                catch (Exception e){
                    System.out.printf(e.toString());
                }
            }
        }
        public InputStream getInputStream() throws FileNotFoundException {
            return new FileInputStream(file_);
        }

        public void cleanUp() {
            if (temporary_) {
                FileUtils.deleteQuietly(file_);
            }
        }

        public boolean isEmpty() {
            return false;
        }
    }

    public void setContent(String s);

    /**
     * Returns a new {@link InputStream} allowing to read the downloaded content.
     * @return the InputStream
     * @throws IOException in case of problem accessing the content
     */
    InputStream getInputStream() throws IOException;

    /**
     * Clean up resources associated to this content.
     */
    void cleanUp();

    /**
     * Returns true if the content is empty.
     * @return true or false
     */
    boolean isEmpty();
}
