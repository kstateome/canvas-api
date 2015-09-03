package edu.ksu.canvas.net;

public class Response {
    private boolean errorHappened = false;
    private int responseCode;
    private String nextLink;
    private String content;

    public boolean getErrorHappened() {
        return errorHappened;
    }

    public void setErrorHappened(boolean errorHappened) {
        this.errorHappened = errorHappened;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getNextLink() {
        return nextLink;
    }

    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Response{" +
                "errorHappened=" + errorHappened +
                ", responseCode=" + responseCode +
                ", nextLink='" + nextLink + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
