<#import "parth/common.ftl" as c>
<#import "parth/_login_and_logout.ftl" as r>

<@c.page>
<h3>${message!}</h3>
    <@r.login "/registration"></@r.login>
</@c.page>