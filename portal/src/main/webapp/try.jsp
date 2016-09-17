<!doctype html>
<script>
function bar(){
   var baz = document.getElementById('foo').value;
   var arr = baz.split(/[\n\r]/g);
    alert(arr);
   // document.getElementById('foo').value = "foo\nbar";
}
</script>
<body onload="bar()">
<form><textarea id="foo" name="foo">

</textarea>
<input type="submit">
</form>
</body>