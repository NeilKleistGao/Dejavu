<!--
 * @FileDescription: 404页面文件
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/4/23
 -->
<template>
  <div>
    <img src="not_found.png" style="width: 70%; height: 50%; margin-left: 15%; margin-right: auto">
    <br/>
    <h3 class="text-center">找不到该页面</h3>
    <div class="flex flex-center" style="margin-top: -30px">
      <q-btn class="text-center" outline color="primary" size="lg" label="返回主页" to="/"/>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Error404'
}
</script>
