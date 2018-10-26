<#macro login path>
     <form action="${path}" method="post">
         <input type="text" name="username" placeholder="User Name">
         <input type="password" name="password" placeholder="Password">
         <input type="hidden" name="_csrf" value="${_csrf.token}">
         <input type="submit" value="Login">
     </form>
</#macro>
<#macro logout>
     <form action="/logout" method="post">
         <input type="hidden" name="_csrf" value="${_csrf.token}">
         <input type="submit" value="Sign Out"/>
     </form>
</#macro>