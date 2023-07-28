package com.example.vignannewsapp;


    public class Review {
        private String title;
        private String description;

        public Review() {
            // Default constructor required for Firebase
        }

        public Review(String title, String description) {
            this.title = title;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }


