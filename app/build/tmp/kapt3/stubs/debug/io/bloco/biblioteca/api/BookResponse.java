package io.bloco.biblioteca.api;

import com.google.gson.annotations.SerializedName;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0004\u0011\u0012\u0013\u0014B\u0005\u00a2\u0006\u0002\u0010\u0002R*\u0010\u0003\u001a\u000e\u0012\b\u0012\u00060\u0005R\u00020\u0000\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0015"}, d2 = {"Lio/bloco/biblioteca/api/BookResponse;", "", "()V", "items", "", "Lio/bloco/biblioteca/api/BookResponse$Item;", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "totalItems", "", "getTotalItems", "()Ljava/lang/Integer;", "setTotalItems", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "ImageLinks", "IndustryIdentifier", "Item", "VolumeInfo", "app_debug"})
public final class BookResponse {
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "items")
    private java.util.List<io.bloco.biblioteca.api.BookResponse.Item> items;
    @org.jetbrains.annotations.Nullable()
    @com.google.gson.annotations.SerializedName(value = "totalItems")
    private java.lang.Integer totalItems;
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.List<io.bloco.biblioteca.api.BookResponse.Item> getItems() {
        return null;
    }
    
    public final void setItems(@org.jetbrains.annotations.Nullable()
    java.util.List<io.bloco.biblioteca.api.BookResponse.Item> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getTotalItems() {
        return null;
    }
    
    public final void setTotalItems(@org.jetbrains.annotations.Nullable()
    java.lang.Integer p0) {
    }
    
    public BookResponse() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR$\u0010\t\u001a\b\u0018\u00010\nR\u00020\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u00a8\u0006\u0010"}, d2 = {"Lio/bloco/biblioteca/api/BookResponse$Item;", "", "(Lio/bloco/biblioteca/api/BookResponse;)V", "id", "", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "volumeInfo", "Lio/bloco/biblioteca/api/BookResponse$VolumeInfo;", "Lio/bloco/biblioteca/api/BookResponse;", "getVolumeInfo", "()Lio/bloco/biblioteca/api/BookResponse$VolumeInfo;", "setVolumeInfo", "(Lio/bloco/biblioteca/api/BookResponse$VolumeInfo;)V", "app_debug"})
    public final class Item {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "id")
        private java.lang.String id;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "volumeInfo")
        private io.bloco.biblioteca.api.BookResponse.VolumeInfo volumeInfo;
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getId() {
            return null;
        }
        
        public final void setId(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final io.bloco.biblioteca.api.BookResponse.VolumeInfo getVolumeInfo() {
            return null;
        }
        
        public final void setVolumeInfo(@org.jetbrains.annotations.Nullable()
        io.bloco.biblioteca.api.BookResponse.VolumeInfo p0) {
        }
        
        public Item() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R&\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\"\u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\b\u0018\u00010\u0012R\u00020\u00138\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R*\u0010\u0018\u001a\u000e\u0012\b\u0012\u00060\u0019R\u00020\u0013\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\tR \u0010\u001c\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R \u0010!\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001e\"\u0004\b#\u0010 \u00a8\u0006$"}, d2 = {"Lio/bloco/biblioteca/api/BookResponse$VolumeInfo;", "", "(Lio/bloco/biblioteca/api/BookResponse;)V", "authors", "", "", "getAuthors", "()Ljava/util/List;", "setAuthors", "(Ljava/util/List;)V", "averageRating", "", "getAverageRating", "()Ljava/lang/Double;", "setAverageRating", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "imageLinks", "Lio/bloco/biblioteca/api/BookResponse$ImageLinks;", "Lio/bloco/biblioteca/api/BookResponse;", "getImageLinks", "()Lio/bloco/biblioteca/api/BookResponse$ImageLinks;", "setImageLinks", "(Lio/bloco/biblioteca/api/BookResponse$ImageLinks;)V", "industryIdentifiers", "Lio/bloco/biblioteca/api/BookResponse$IndustryIdentifier;", "getIndustryIdentifiers", "setIndustryIdentifiers", "publishedDate", "getPublishedDate", "()Ljava/lang/String;", "setPublishedDate", "(Ljava/lang/String;)V", "title", "getTitle", "setTitle", "app_debug"})
    public final class VolumeInfo {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "title")
        private java.lang.String title;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "authors")
        private java.util.List<java.lang.String> authors;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "publishedDate")
        private java.lang.String publishedDate;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "averageRating")
        private java.lang.Double averageRating;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "industryIdentifiers")
        private java.util.List<io.bloco.biblioteca.api.BookResponse.IndustryIdentifier> industryIdentifiers;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "imageLinks")
        private io.bloco.biblioteca.api.BookResponse.ImageLinks imageLinks;
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getTitle() {
            return null;
        }
        
        public final void setTitle(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<java.lang.String> getAuthors() {
            return null;
        }
        
        public final void setAuthors(@org.jetbrains.annotations.Nullable()
        java.util.List<java.lang.String> p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getPublishedDate() {
            return null;
        }
        
        public final void setPublishedDate(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.Double getAverageRating() {
            return null;
        }
        
        public final void setAverageRating(@org.jetbrains.annotations.Nullable()
        java.lang.Double p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.util.List<io.bloco.biblioteca.api.BookResponse.IndustryIdentifier> getIndustryIdentifiers() {
            return null;
        }
        
        public final void setIndustryIdentifiers(@org.jetbrains.annotations.Nullable()
        java.util.List<io.bloco.biblioteca.api.BookResponse.IndustryIdentifier> p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final io.bloco.biblioteca.api.BookResponse.ImageLinks getImageLinks() {
            return null;
        }
        
        public final void setImageLinks(@org.jetbrains.annotations.Nullable()
        io.bloco.biblioteca.api.BookResponse.ImageLinks p0) {
        }
        
        public VolumeInfo() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006\f"}, d2 = {"Lio/bloco/biblioteca/api/BookResponse$IndustryIdentifier;", "", "(Lio/bloco/biblioteca/api/BookResponse;)V", "identifier", "", "getIdentifier", "()Ljava/lang/String;", "setIdentifier", "(Ljava/lang/String;)V", "type", "getType", "setType", "app_debug"})
    public final class IndustryIdentifier {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "type")
        private java.lang.String type;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "identifier")
        private java.lang.String identifier;
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getType() {
            return null;
        }
        
        public final void setType(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getIdentifier() {
            return null;
        }
        
        public final void setIdentifier(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        public IndustryIdentifier() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006\f"}, d2 = {"Lio/bloco/biblioteca/api/BookResponse$ImageLinks;", "", "(Lio/bloco/biblioteca/api/BookResponse;)V", "smallThumbnail", "", "getSmallThumbnail", "()Ljava/lang/String;", "setSmallThumbnail", "(Ljava/lang/String;)V", "thumbnail", "getThumbnail", "setThumbnail", "app_debug"})
    public final class ImageLinks {
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "smallThumbnail")
        private java.lang.String smallThumbnail;
        @org.jetbrains.annotations.Nullable()
        @com.google.gson.annotations.SerializedName(value = "thumbnail")
        private java.lang.String thumbnail;
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getSmallThumbnail() {
            return null;
        }
        
        public final void setSmallThumbnail(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        @org.jetbrains.annotations.Nullable()
        public final java.lang.String getThumbnail() {
            return null;
        }
        
        public final void setThumbnail(@org.jetbrains.annotations.Nullable()
        java.lang.String p0) {
        }
        
        public ImageLinks() {
            super();
        }
    }
}