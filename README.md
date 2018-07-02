<!-- Project Environments -->

<h2>Project Environments</h2>
<table>
  <tr>
    <th colspan="2">Project Environments</th>
  </tr>
  <tr>
    <th>DB</th>
    <td>Redis on Linux</td>
  </tr>
  <tr>
    <th>Language</th>
    <td>Java (Spring Boot)</td>
  </tr>
  <tr>
    <th>Server</th>
    <td>Tomcat</td>
  </tr>
  <tr>
    <th>Library</th>
    <td>Jedis (for Redis)</td>
  </tr>
  <tr>
    <th>CSS</th>
    <td><a href="https://github.com/ArunMichaelDsouza/CSS-Mint">CSS-Mint</a></td>
  </tr>
  <tr>
    <th>Build</th>
    <td>Maven</td>
  </tr>
</table>
<br/>
<!-- DB Data Model -->
<h2>DB Data Model</h2>
<table>
  <tr>
    <th colspan="3">key-hash</th>
  </tr>
  <tr>
    <th>key</th>
    <th>field</th>
    <th>value</th>
  </tr>
  <tr>    
    <td rowspan="4">[user id]</td>
    <td>id</td>
    <td>[user id]</td>
  </tr>
  <tr>
    <td>pw</td>
    <td>[user password]</td>
  </tr>
  <tr>
    <td>name</td>
    <td>[user name]</td>
  </tr>
  <tr>
    <td>email</td>
    <td>[user e-mail]</td>
  </tr>
</table>
<!-- Features -->
<h2>Features</h2>
<ul>
  <li>회원가입</li>
  <li>로그인</li>
  <li>로그아웃</li>
  <li>회원탈퇴</li>
  <li>회원 정보 수정</li>
  <li>회원가입 데이터 유효성 검사</li>
</ul>

<!-- Notes -->
<h2>Notes</h2>
<ul>
  <li>vagrant 로컬 포트 포워딩 설정 : https://sejoung.github.io/2018/06/vagrant_redis</li>
</ul>
