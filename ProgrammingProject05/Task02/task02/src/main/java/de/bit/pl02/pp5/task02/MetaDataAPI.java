package de.bit.pl02.pp5.task02;

/** MetaDataAPI receives metadata from the SQLite database upon querying by the 
 * getForAPI method in the database class. 
 * 
 * @author Shreya Kapoor
 * @author Sophia Krix
 * @author Gemma van der Voort 
 * 
 * 
 */
public class MetaDataAPI {
	
		private int id;
		private String author;
		private String title;
		private String link;
		
		/** Class constructor
		 * MetaDataAPI receives metadata from the SQLite database upon querying by the 
		 * getForAPI method in the database class. 
		 * @param id
		 * @param author
		 * @param title
		 * @param link
		 */
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

