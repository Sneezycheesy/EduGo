<h1>EduGo</h1>
<p>A simple version of the very popular game Go</p>

<p>The game in action:</p>
<img src="./images/Game.png" style="height: 100px"/>

<p>Before you get there, you can select the board size:</p>
<img src="./images/BoardSelection.png" /><br/>

<p>
  What works:
  <ul>
    <li> Placing black stones </li>
    <li> The bot places a white stone after 1.5 seconds </li>
    <li> Completely surrounded <i>single</i> stones get removed </li>
    <li> Stones cannot be places in a <i>single</i> surrounded position </li>
  </ul>
</p>

<p>
  What doesn't work:
  <ul>
    <li> Surrounded <i>groups</i> of stones do not get removed</li>
    <li> Stones can be placed in surrounded <i>groups</i> of positions</li>
  </ul>
</p>

<p>
  Bugs:
  <ul>
    <li> The player can still place stones while the "AI" is making up its mind (The 1,5s delay)</li>
  </ul>
</p>

<h4> Small preview </h4>
<img src="./images/letsplay.gif" style="width: 800px"/>
