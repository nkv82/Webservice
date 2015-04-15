package com.nv;

public class Track {

	String title;
	String singer;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	@Override
	public String toString() {
		//return "{ \"Album\":{\"singer\":\""+ singer + "\",\"title\":\""+ title +" \"}}";//"Track [title=" + title + ", singer=" + singer + "]";
	return "Track [title="+title+",singer="+singer+"]";
	}

/*	{
		"lotto":{
		 "lottoId":5,
		 "winning-numbers":[2,45,34,23,7,5,3],
		 "winners":[{
		   "winnerId":23,
		   "numbers":[2,45,34,23,3,5]
		 },{
		   "winnerId":54,
		   "numbers":[52,3,12,11,18,22]
		 }]
		}
		}*/
}
