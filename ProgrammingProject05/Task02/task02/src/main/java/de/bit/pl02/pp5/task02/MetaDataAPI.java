package de.bit.pl02.pp5.task02;

public class MetaDataAPI {
	// TODO returns list of id's and metadata!
		private int id;
		private String author;
		private String title;
		private String link;

		public MetaDataAPI(int id, String author, String title, String link) {//, String link, String fileName) {
			this.id = id;
			this.author = author;
			this.title =title;
			this.link = link;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}
	}

