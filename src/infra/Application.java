package infra;

import controller.Controller;

import java.util.Scanner;

public class Application {
    private Scanner sc=Container sc;
    private boolean isActive =true;
    public void run(){
        while(isActive){
            System.out.println("명령아:");
            String inputUri=sc.nextLine().trim();
            Request request=new Request(inputUri);
            Controller controller=getController(request.getControllerCode());
            if(controller!=null){
                controller.excute(request);
            }
            else{
                System.out.println("올바른 URI를 입력하세요");
            }

        }
    }
    public Controller getController(String code){
        switch(code){
            case "system":
                return Container.systemcontroller;
            default:
                return null;
        }
    }

}
