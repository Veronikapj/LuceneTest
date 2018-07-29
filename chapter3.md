# LuceneTest
루씬 소스 돌려보기
<br><br>
3장 검색 <br>
루씬의 핵심 검색 API<br><br>
<b>IndexSearcher></b><br>
색인을 검색하는 기능을 담당하는 클래스. 모든 검색 기능은 IndexSearcher 인스턴스의 여러가지 search메소드를 통해 진행한다.<br>
<b>Query</b><br>
실제 Query 하위 클래스에서 특정 질의 기능을 구현한다. IndexSearcher의 search 메소드에 Query객체를 인자로 넘겨줄 수 있다.<br>
<b>QueryParser</b><br>
사람이 직접 입력하고 사람이 한눈에 알아 볼 수 있는 텍스트 형태의 질의를 분석해 루씬 Query 객체로 변환한다.<br>
<b>TopDocs</b><br>
IndexSearcher.search 메소드에서 검색한 결과 중 연관도 점수가 가장 높은 결과 문서를 담는 클래스이다.<br>
색인에서 질의를 생성하면 연관도 점수를 기준으로 내림차순으로 정렬된 ScoreDocs 목록이 담긴 TopDocs 인스턴스를 넘겨 받는다.<br>
<b>ScoreDocs</b><br>
TopDocs 클래스에 담긴 검색결과 하나를 나타내는 클래스이다.<br>


