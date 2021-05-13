<!--
 * @FileDescription: 搜索页面
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/5/13
 -->
<template>
  <q-page class="flex-center">
    <div class="q-pa-md flex-center" >
      <div class="q-gutter-y-md" style="width: 60%; margin-left: 20%;">
        <div class="row" style="margin-top: 2em; margin-bottom: -2em">
          <div class="col-8">
            <q-breadcrumbs>
              <q-breadcrumbs-el label="二手车搜索"/>
              <q-breadcrumbs-el label="北京"/>
              <q-breadcrumbs-el v-if="brand !== ''" :label="brand"/>
              <q-breadcrumbs-el v-if="body !== ''" :label="body"/>
              <q-breadcrumbs-el v-if="price !== ''" :label="price + '万元'"/>
              <q-breadcrumbs-el v-if="text !== ''" :label="'搜索：' + text"/>
            </q-breadcrumbs>
          </div>
          <div class="col-4">
            <q-input outlined bottom-slots dense label="搜索" v-model="text">
              <template slot="append">
                <q-icon name="search" />
              </template>
            </q-input>
          </div>
        </div>
        <q-tabs
          v-model="brand"
          inline-label
          class="bg-primary text-white shadow-2"
        >
          <q-tab name="" label="不限"/>
          <q-tab name="大众" label="大众" />
          <q-tab name="宝马" label="宝马" />
          <q-tab name="奔驰" label="奔驰" />
          <q-tab name="丰田" label="丰田" />
          <q-tab name="本田" label="本田" />
          <q-tab name="奥迪" label="奥迪" />
          <q-btn-dropdown auto-close stretch flat label="More...">
            <q-list>
              <q-item clickable @click="brand = '现代'">
                <q-item-section>现代</q-item-section>
              </q-item>
              <q-item clickable @click="brand = '马自达'">
                <q-item-section>马自达</q-item-section>
              </q-item>
              <q-item clickable @click="brand = '哈佛'">
                <q-item-section>哈佛</q-item-section>
              </q-item>
              <q-item clickable @click="brand='比亚迪'">
                <q-item-section>比亚迪</q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </q-tabs>
        <q-tabs
          v-model="body"
          inline-label
          class="bg-primary text-white shadow-2"
        >
          <q-tab name="" label="不限"/>
          <q-tab name="豪华轿车" label="豪华轿车" />
          <q-tab name="微型车" label="微型车" />
          <q-tab name="箱型车" label="箱型车" />
          <q-tab name="大巴车" label="大巴车" />
          <q-tab name="敞篷车" label="敞篷车" />
          <q-tab name="双门汽车" label="双门汽车" />
          <q-btn-dropdown auto-close stretch flat label="More...">
            <q-list>
              <q-item clickable @click="body = '商务车'">
                <q-item-section>商务车</q-item-section>
              </q-item>
              <q-item clickable @click="body = '搅拌车'">
                <q-item-section>搅拌车</q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </q-tabs>
        <q-tabs
          v-model="price"
          inline-label
          class="bg-primary text-white shadow-2"
        >
          <q-tab name="" label="不限"/>
          <q-tab name="0-3" label="3万以下" />
          <q-tab name="3-5" label="3-5万" />
          <q-tab name="5-7" label="5-7万" />
          <q-tab name="7-9" label="7-9万" />
          <q-tab name="9-12" label="9-12万" />
          <q-tab name="12-16" label="12-16万" />
          <q-btn-dropdown auto-close stretch flat label="More...">
            <q-list>
              <q-item clickable @click="price = '16-20'">
                <q-item-section>16-20万</q-item-section>
              </q-item>
              <q-item clickable @click="price = '20-'">
                <q-item-section>20万以上</q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </q-tabs>
      </div>
    </div>
    <div class="q-pa-md row items-start q-gutter-md" style="width: 60%; margin-left: 20%">
      <q-card class="my-card" v-for="item in carList" :key="item.id" @click="goDetail(item.id)">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{item.manufacturer}} {{item.model}}</div>
            <div class="text-subtitle1">{{item.server}}年</div>
            <div></div>
          </div>
        </q-img>

        <q-card-section>
          <div>
            <del class="text-h6">{{item.guild_price}}万元</del>
            <span class="text-subtitle1 text-red">{{item.price}}万元</span>
          </div>
        </q-card-section>
      </q-card>
    </div>
    <div class="q-pa-lg flex flex-center">
      <q-pagination
        v-model="current"
        color="blue"
        :max="max_page"
        :max-pages="max_page"
        :boundary-numbers="false"
        direction-links
      />
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'Search',
  data () {
    return {
      text: '', // 搜索文字内容
      brand: '', // 品牌筛选
      body: '', // 车型筛选
      price: '', // 价格筛选
      carList: [ // 车辆信息列表
        {
          id: 1,
          model: '2019款C200',
          manufacturer: '奔驰',
          server: 1.2,
          guild_price: 24,
          price: '23.88',
          img: 'car_details_test_img/1.png'
        }, {
          id: 1,
          model: '2019款C200',
          manufacturer: '奔驰',
          server: 1.2,
          guild_price: 24,
          price: '23.88',
          img: 'car_details_test_img/1.png'
        }, {
          id: 1,
          model: '2019款C200',
          manufacturer: '奔驰',
          server: 1.2,
          guild_price: 24,
          price: '23.88',
          img: 'car_details_test_img/1.png'
        }, {
          id: 1,
          model: '2019款C200',
          manufacturer: '奔驰',
          server: 1.2,
          guild_price: 24,
          price: '23.88',
          img: 'car_details_test_img/1.png'
        }, {
          id: 1,
          model: '2019款C200',
          manufacturer: '奔驰',
          server: 1.2,
          guild_price: 24,
          price: '23.88',
          img: 'car_details_test_img/1.png'
        }
      ],
      current: 1, // 当前页码
      max_page: 10
    }
  },
  methods: {
    goDetail (id) {
      window.location = '/#/car/' + id.toString()
    }
  }
}
</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px
</style>
