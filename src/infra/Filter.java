package infra;

import config.ApplicationConfig;

public class Filter {

    public Request request;

    public Filter(Request request){
        this.request = request;
    }

    private boolean isAnonymous(String url){

        String[] anonymousUrlList =  ApplicationConfig.anonymousUriList;

        for(String regUrl : anonymousUrlList){
            if(regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }

    private boolean isNeedAuth(String url){

        String[] hasAuthUrlList = ApplicationConfig.hasAuthUriList;

        for(String regUrl : hasAuthUrlList){
            if(regUrl.equals(url)){
                return true;
            }
        }
        return false;
    }

    private String sorting(String url){

        if(isAnonymous(url)){
            return "ANONYMOUS";
        }

        if(isNeedAuth(url)){
            return "HASAUTH";
        }

        return "PERMITALL";

    }

    public boolean isValidRequest(){

        String originUrl = request.getOriginUri();

        String sortCode = sorting(originUrl);
        boolean isLogon = request.isLogon();

        if(sortCode.equals("ANONYMOUS")){
            if(isLogon){
                return false;
            }
        } else if (sortCode.equals("HASAUTH")) {
            if(!isLogon){
                return false;
            }
        }

        return true;

    }



}
