# project2

# ToDoListCommand
- ArrayList 를 사용해 배열을 저장.
- addSchedule 메서드를 통해 연,월,일을 입력 받아 dateStr 변수에 저장 후 setDate에 저장
  저장을 하는데 연,월을 먼저 입력받고 일을 입력 받는데 연,월,일을 동시에 넣는데 넘어가는 버그 발생
  버그를 잡기 위해 if문으로 java util에 있는 !Pattern.matches("\\d{4}-\\d{2}", dateStr) 을 이용해
  year(\\d{4}) , month(\\d{2})에 맞게 입력하지 않는다면 continue 로 다시 제대로 된 연,월을 입력받게 함
- 
