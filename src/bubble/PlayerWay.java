package bubble;

/**
 * [enum] 플레이어의 방향ㅅ강태
 * 
 * enum 을 사용하는 이유
 *  - boolean 두개(is Left,isRight)로 방향을 관리하면 
 *  둘다 true 가 되는 잘못된 상태가 생길수 있음
 *  enum은 정해진 값 중 하난만 가질수 있어서 안전함
 *  
 *  사용방법:
 *  playerWay = playerWay.LEFT // 값설정
 *  if(playerWay == playerWay.LEFT) {} // 값비교
 *
 * // 왜 사용할까?
 */

public enum PlayerWay { // 범위를 지정한다
    LEFT, RIGHT
}