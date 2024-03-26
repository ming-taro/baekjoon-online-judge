class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int totalHealth = health;
        
        for (int i = 0; i < attacks.length; i++) {
            int currentTime = attacks[i][0];
            int damage = attacks[i][1];
            
            totalHealth -= damage;  //공격을 받아서 체력 깎임
            if (totalHealth <= 0) { //공격 도중 사망
                return -1;
            }
            if (i == attacks.length - 1) {
                return totalHealth;
            }
            
            //1초다음이 3초 공격인 경우 회복은 2초에만 가능하다
            int bandageRecovery = (attacks[i + 1][0] - attacks[i][0] - 1) * bandage[1];
            int extraRecovery = (attacks[i + 1][0] - attacks[i][0] - 1) / bandage[0] * bandage[2];
            
            totalHealth = (totalHealth + bandageRecovery + extraRecovery);
            if (totalHealth > health) {
                totalHealth = health;
            }
        }
        
        return totalHealth;
    }
}
/*
붕대감기 = t초 동안 붕대를 감음 + 1초마다 x만큼 체력 회복
t초 연속으로 붕대 감기 성공 -> y만큼 체력 추가 회복
>>>최대 체력 존재

기술쓰는 중 공격 당함 -> 기술 취소, 체력 깎임 -> 즉시 붕대 감기 + 연속 시간 0으로 초기화

주어진 데이터 : 붕대감기(시전 시간, 초당 회복량, 추가 회복량) / 최대 체력 / 몬스터 공격 패턴(시간, 피해량)
return 생존시 체력 or -1
*/