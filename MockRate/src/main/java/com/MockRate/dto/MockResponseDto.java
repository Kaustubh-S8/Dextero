package com.MockRate.dto;


public class MockResponseDto {
	 private Integer mid;
	    private Integer uid;
	    private String topic;
	    private Double MockAvgScore;
	    public MockResponseDto() {
			// TODO Auto-generated constructor stub
		}
		public MockResponseDto(Integer mid, Integer uid, String topic, Double mockAvgScore) {
			super();
			this.mid = mid;
			this.uid = uid;
			this.topic = topic;
			MockAvgScore = mockAvgScore;
		}
		public Integer getMid() {
			return mid;
		}
		public void setMid(Integer mid) {
			this.mid = mid;
		}
		public Integer getUid() {
			return uid;
		}
		public void setUid(Integer uid) {
			this.uid = uid;
		}
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public Double getMockAvgScore() {
			return MockAvgScore;
		}
		public void setMockAvgScore(Double mockAvgScore) {
			MockAvgScore = mockAvgScore;
		}
	    
}
