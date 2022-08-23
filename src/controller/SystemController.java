package controller;

import infra.Request;

public class SystemController implements Controller{

    @Override
    public void excute(Request request) {
        switch (request.getTarget()) {
            case "exit":
                exitApplication();
                break;
            default:
                break;
        }

    }

    private void exitApplication() {
    }
}
