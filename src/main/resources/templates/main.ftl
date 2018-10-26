<#import "parth/common.ftl" as c>
<#import "parth/_login_and_logout.ftl" as l>
<@c.page>
<div>
    <@l.logout></@l.logout>
</div>
<form action="/main" method="post" enctype="multipart/form-data">
    <input type="text" name="text" placeholder="text" >
    <input type="text" name="tag" placeholder="tag">
    <input type="file" name="file">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">save</button>
</form>
<form action="/main" method="get">
    <input type="text" name="filter" value="${filter!}">
    <button type="submit">Out</button>
</form>
<table>
    <thead>
    <tr>
        <th>Text</th>
        <th>Tag</th>
        <th>Author</th>
    </tr>
    </thead><tbody>
    <#list messages as message >
    <tr>
        <td>${(message.text)!}</td>
        <td>${(message.tag)!}</td>
        <td>${(message.author.username)!}</td>
        <td>
            <#if message.filename??>
            <img src="/img/${message.filename}">
        </#if>
        </td>
    </tr>
    </#list>
</tbody>
</table>
</@c.page>