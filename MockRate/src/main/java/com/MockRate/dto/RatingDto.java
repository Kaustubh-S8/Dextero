package com.MockRate.dto;

public class RatingDto {
	 private Integer rid;
	    private Integer uid;
	    private Integer mid;
	    private Integer communication;
	    private Integer confidence;
	    private Integer content;
	    private Integer interaction;
	    private Integer liveliness;
	    private Double totalScore;
	    
	    public RatingDto() {
	    }

		public RatingDto(Integer rid, Integer uid, Integer mid, Integer communication, Integer confidence,
				Integer content, Integer interaction, Integer liveliness, Double totalScore) {
			super();
			this.rid = rid;
			this.uid = uid;
			this.mid = mid;
			this.communication = communication;
			this.confidence = confidence;
			this.content = content;
			this.interaction = interaction;
			this.liveliness = liveliness;
			this.totalScore = totalScore;
		}

		public Integer getRid() {
			return rid;
		}

		public void setRid(Integer rid) {
			this.rid = rid;
		}

		public Integer getUid() {
			return uid;
		}

		public void setUid(Integer uid) {
			this.uid = uid;
		}

		public Integer getMid() {
			return mid;
		}

		public void setMid(Integer mid) {
			this.mid = mid;
		}

		public Integer getCommunication() {
			return communication;
		}

		public void setCommunication(Integer communication) {
			this.communication = communication;
		}

		public Integer getConfidence() {
			return confidence;
		}

		public void setConfidence(Integer confidence) {
			this.confidence = confidence;
		}

		public Integer getContent() {
			return content;
		}

		public void setContent(Integer content) {
			this.content = content;
		}

		public Integer getInteraction() {
			return interaction;
		}

		public void setInteraction(Integer interaction) {
			this.interaction = interaction;
		}

		public Integer getLiveliness() {
			return liveliness;
		}

		public void setLiveliness(Integer liveliness) {
			this.liveliness = liveliness;
		}

		public Double getTotalScore() {
			return totalScore;
		}

		public void setTotalScore(Double totalScore) {
			this.totalScore = totalScore;
		}

	    
}
