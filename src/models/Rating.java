package models;

import utils.Utilities;

public class Rating {

    private int numberOfStars = 0;
    private String raterName = "<rater name>";
    private String ratingComment = "<no comment>";

    public Rating(int numberOfStars, String raterName, String ratingComment) {
        setNumberOfStars(numberOfStars);
        setRaterName(raterName);
        setRatingComment(ratingComment);
    }

    public int getNumberOfStars() {
        return numberOfStars;
    }
    public String getRatingComment() {
        return ratingComment;
    }
    public String getRaterName() {
        return raterName;
    }

    public void setNumberOfStars(int numberOfStars) {
        if (Utilities.validRange(numberOfStars, 1, 5)) {
            this.numberOfStars = numberOfStars;
        }
    }

    public void setRatingComment(String ratingComment) {
        if (!ratingComment.equals("")) {
            this.ratingComment = ratingComment;
        }
    }

    public void setRaterName(String raterName) {
        if (!raterName.equals("")) {
            this.raterName = raterName;
        }
    }

    @Override
    public String toString() {
        String ratingHeader = numberOfStars + " stars (by " + raterName + ").";
        if (ratingComment.isEmpty())
            return ratingHeader;
        else
            return ratingHeader + "\"" + ratingComment + '\"';
    }
}