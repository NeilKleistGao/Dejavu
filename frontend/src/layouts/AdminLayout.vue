<!--
 * @FileDescription: 管理端布局文件
 * @Author: NeilKleistGao
 * @Date: 2021/6/4
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/6/4
 -->
<template>
  <q-layout view="hHh lpR fFf">
    <q-header elevated>
      <q-toolbar class="bg-primary text-white shadow-2" style="padding: 15px 10px">
        <q-toolbar-title>
          <q-icon class="iconfont icon-drifting"></q-icon>
          Dejavu二手车交易平台-管理端
        </q-toolbar-title>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
export default {
  name: 'AdminLayout'
}
</script>

<style scoped>

</style>
