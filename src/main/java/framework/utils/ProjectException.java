package framework.utils;

public class ProjectException extends RuntimeException {

    public ProjectException(String msg){
        super(msg+"\n");
    }
}
