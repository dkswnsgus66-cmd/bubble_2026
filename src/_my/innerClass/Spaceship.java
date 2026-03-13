package _my.innerClass;



    // 정적 내부 클래스 연습
    public class Spaceship {

        private Engine engine;

        // 엔진을 추가한다
        public void addEngine(Engine engine) {
            this.engine = engine;
            System.out.println("엔진1개 장착");
        }

        public void startSpaceShip() {
            if(engine == null) {
                System.out.println("엔진을 먼저 장착해주세요");
            } else {
                System.out.println("우주여행을 출발 합니다");
            }
        }

        // 논리적으로 강하게 그룹화 시킬 때 내부 클래스를 사용할 수 있다.
        // 정적 내부 클래스 Engine
        public static class Engine {

            private static int engineCount = 0;
            private int serialNumber;

            public Engine() {
                this.serialNumber = ++engineCount;
            }

            public void start() {
                System.out.println("Engine " + serialNumber + " 동작 합니다");
            }
        } // end of inner static class

    }


