/*
 * ************************************************************************
 *  Copyright (c) 2018 @alxgcrz <alxgcrz@outlook.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ***********************************************************************
 */

package com.example.java;

import java.net.URI;
import java.util.Date;


public class BlogPostJava {

    private final String title;

    private final URI url;

    private final String description;

    private final Date publishDate;


    public BlogPostJava(String title, URI url, String description, Date publishDate) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.publishDate = publishDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlogPostJava blogPost = (BlogPostJava) o;

        if (title != null ? !title.equals(blogPost.title) : blogPost.title != null)
            return false;
        if (url != null ? !url.equals(blogPost.url) : blogPost.url != null) return false;
        if (description != null ? !description.equals(blogPost.description) : blogPost.description != null)
            return false;
        return publishDate != null ? publishDate.equals(blogPost.publishDate) : blogPost.publishDate == null;
    }


    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "[JAVA] BlogPostJava{" +
                "title='" + title + '\'' +
                ", url=" + url +
                ", description='" + description + '\'' +
                ", publishDate=" + publishDate +
                '}';
    }


    public String getTitle() {
        return title;
    }


    public URI getUrl() {
        return url;
    }


    public String getDescription() {
        return description;
    }


    public Date getPublishDate() {
        return publishDate;
    }
}
