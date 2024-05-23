import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        int N = phone_book.length;
        Set<String> numbers = new HashSet<>();
        Map<Integer, Set<String>> sameLengthNumbers = new HashMap<>();
        Set<Integer> lengthSize = new HashSet<>();
        
        for (String phone: phone_book) {
            lengthSize.add(phone.length());
            if (sameLengthNumbers.containsKey(phone.length())) {
                sameLengthNumbers.get(phone.length()).add(phone);
            } else {
                Set<String> tmp = new HashSet<>();
                tmp.add(phone);
                sameLengthNumbers.put(phone.length(), tmp);
            }
        }
        
        Iterator<Integer> iter;
        
        for (int i = 0; i < N; i++) {
            iter = lengthSize.iterator();	
            while(iter.hasNext()) {
                int size = iter.next();
                if (phone_book[i].length() > size) {
                    numbers.add(phone_book[i].substring(0, size));
                }
            }
        }
        
        for (int i = 0; i < N; i++) {
            if (numbers.contains(phone_book[i])) {
                return false;
            }
        }
        
        return true;
    }
}
/*
123 456 789

1 12 123 4 45 456 7 78 789

*/