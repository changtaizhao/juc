package com.chantai.juc.basic;

/**
 * @Auther: changtai.zhao
 * @Date: 2018-12-17 22:05
 * @Description:
 */
public class TestStop {

    public static User user = new User();

    public static class User{
        private int id;
        private String name;
        public User(){
            id = 0;
            name = "0";
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User [id=" + id + ",name=" + name + "]";
        }
    }

    public static class ChangeObjectThread extends Thread{
        //stop
        volatile boolean stopme = false;

        public void stopMe(){
            stopme = true;
        }

        @Override
        public void run() {
            while (true){
                synchronized (user){
                    if(stopme){
                        System.out.println("exit by stop me");
                        break;
                    }

                    int v = (int)(System.currentTimeMillis()/1000);
                    user.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    user.setName(String.valueOf(v));
                    Thread.yield();
                }
            }
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while(true){
                synchronized (user){
                    if(user.getId() != Integer.parseInt(user.getName())){
                        System.out.println(user.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true){
            Thread thread = new ChangeObjectThread();
            thread.start();
            Thread.sleep(150);
            //thread.stop();
            ((ChangeObjectThread) thread).stopMe();
        }
    }

}
