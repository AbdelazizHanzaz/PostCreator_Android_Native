package com.example.postcreator.pojo;

public class MediaFile {

      private int id;
      private String mediaUrl;

      public boolean isImage(){
          return mediaUrl.endsWith("png") || mediaUrl.endsWith("jpg");
      }

      public boolean isSound(){
          return  mediaUrl.endsWith("mp3");
      }


}
