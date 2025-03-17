package dto;

public class ExamDTO {
    private int examId;
    private String examTitle;
    private String subject;
    private int totalMarks;
    private int duration;

    public ExamDTO(int examId, String examTitle, String subject, int totalMarks, int duration) {
        this.examId = examId;
        this.examTitle = examTitle;
        this.subject = subject;
        this.totalMarks = totalMarks;
        this.duration = duration;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getExamTitle() {
        return examTitle;
    }

    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    
    
}