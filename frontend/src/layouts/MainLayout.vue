<!--
 * @FileDescription: 布局文件，包含一个导航栏
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/5/12
 -->

<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar class="bg-primary text-white shadow-2" style="padding: 15px 10px">
        <q-toolbar-title @click="goHome">
          <q-icon class="iconfont icon-drifting"></q-icon>
          Dejavu二手车交易平台
        </q-toolbar-title>

        <q-btn flat class="q-mr-xs" label="我要买车"/>
        <q-btn flat class="q-mr-xs" label="我要卖车"/>
        <q-btn flat class="q-mr-xs" :label="'当前城市：' + city"/>
        <q-separator vertical inset/>
        <q-btn flat class="q-mr-xs" label="登录"/>
        <q-btn flat class="q-mr-xs" label="注册"/>
      </q-toolbar>
    </q-header>
    <q-page-container>
      <router-view />
    </q-page-container>
    <div>
      <q-toolbar class="bg-primary text-white shadow-2" style="padding: 15px 10px">
        <q-toolbar-title @click="goHome">
          <q-icon class="iconfont icon-drifting"></q-icon>
          <span class="text-h6">Dejavu二手车交易平台</span>
          <br/>
          <span class="text-subtitle2">Copyright 2021 www.dejavu.com All Rights Reserved</span>
        </q-toolbar-title>
      </q-toolbar>
    </div>
  </q-layout>
</template>

<script>
export default {
  name: 'MainLayout',
  data () {
    return {
      city: '北京' // 当前城市名称
    }
  },
  methods: {
    /**
     * @description: 重定向回主页
     * @return void
     */
    goHome () {
      window.location = '/'
    }
  }
}
</script>
