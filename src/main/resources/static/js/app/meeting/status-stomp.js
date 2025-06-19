

/* 2차 프로토콜 사용 > 단독으로 사용 불가 */

 document.addEventListener("DOMContentLoaded", ()=>{
  // 클라이언트 생성
  const client = new StompJs.Client({
    // 전이중 통신구조 웹소켓 개방 > 요청이 한번은 넘어가야함 (http기반이라서)
    brokerURL: 'ws://localhost/ws/stomp',
    debug: function (str) {
      console.log(str);
    },
    reconnectDelay: 5000,
    heartbeatIncoming: 4000,
    heartbeatOutgoing: 4000,
  });
  
   /*
     STOMP는 메서드를 frame의 형태로 표현함. > 3가지 형태
     1. frame: destination(서비스 식별자) + 2. header(metadata) + 3. body(message)
     destination에는 convention이 있음 : /topic(보통 단체문자 - boradcasting)
                                /queu(한명의 구독자를 대상으로 한 메세지 발행)
  */
  // 서비스 구독 설정 넣기 - 실제로는 액티베이션이 동작하고 난 후에 작동함(즉, 스톰프가 활성화되고 난 후에 실행)
  client.onConnect = function (frame) {
    // 어떤 서비스 구독할건지 식별자 넣어줘야함, 현재 웹상에서 이거 실행 > 즉 식별자는 url
    // 단체문자 발행
    client.subscribe("/topic/status", function({body}){
     console.log("구독한 메세지: ", body);
	 const vo = JSON.parse(body);
     contentarea.innerHTML = vo.message;
    });
    
    // 개인 한명에게 메세지 발행
    /*client.subscribe("/queue/status", function(){
     
    });*/
  };
  // 생성한 클라이언트 활성화
  client.activate();

  
  // attr은 무조건 속성값ㄹ을 문자열로 줘야함
  $(".edit").prop("contenteditable", true);
  $("#sendbtn").on("click", ()=>{
	const senderTxt = sender.value;
 	const message = contentarea.innerHTML;   // contentarea 한테 테이블 소스 꺼냄
	const body = {
		sender: senderTxt
		, message: message
	}
	 client.publish({
	    destination: "/app/status"
	    , header: {}
	    , body: JSON.stringify(body)
	 })
  })
})