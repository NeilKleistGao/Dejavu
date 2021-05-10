<!--
 * @FileDescription: 主页文件
 * @Author: ZoeChou
 * @Date: 2021/4/19
 * @LastEditors: ZoeChou
 * @LastEditTime: 2021/5/9
 -->
<template>
  <q-page class="flex flex-center">
    <!--ADD YOUR VUE CODE HERE-->
    <div class="parent" style="height:80px;"></div>
    <q-form @submit="gotoSearch">
      <div class="parentSearch">
        <div class="child" style="height:70px; width:550px;">
          <q-input outlined v-model="message" label="请输入关键字，例如：君越 2.4L" style="height:50px;"/>
        </div>
        <div class="child">
          <q-btn label="搜索" v-on:click="gotoSearch" type="submit" color="primary" style="width:100px; height:55px;"/>
        </div>
      </div>
    </q-form>
    <br/>
    <div class="parentBuySell">
      <div class="child" style="width:700px"></div>
      <div class="child" style="width:500px; height:280px;"></div>
    </div>
    <br/>
    <div class="parentQA">
      <div class="child" style="width:800px; height:300px;"></div>
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'PageIndex',

  data: function () {
    return {
      message: ''
    }
  },

  methods: {
    gotoSearch () {
      this.$router.push({
        path: '/search',
        query: {
          message: this.message
        }
      })
    }
  }
}
</script>

<style>
  .parent, .parentSearch, .parentBuySell, .parentQA {
    display: flex;
    width: 1200px;
    justify-content: center;
    align-content: center;
    align-items: stretch;
  }

  .child {
    width: 100px;
    outline: solid 1px;
  }
</style>
