<!--
 * @FileDescription: 搜索页面
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/4/19
 -->
<template>
  <q-page class="flex-center">
    <!--ADD YOUR VUE CODE HERE-->
    <div style="width:10px"/>
    <div class="q-gutter-y-md column" style="width: 100%" >
      <q-toolbar class="bg-primary text-white rounded-borders">
        <q-avatar class="gt-xs">
          <img src="https://cdn.quasar.dev/logo/svg/quasar-logo.svg">
        </q-avatar>

        <q-space />

        <q-input dark dense standout v-model="text" input-class="text-right" class="q-ml-md">
          <template v-slot:append>
            <q-icon v-if="text === ''" name="search" />
            <q-icon v-else name="clear" class="cursor-pointer" @click="text = ''" />
          </template>
        </q-input>
      </q-toolbar>
    </div>
    <div style="width: 1%"/>
    <div class="q-pa-md flex-center" >
      <div class="q-gutter-y-md" style="max-width: 80%">
        <q-tabs
          v-model="brand"
          inline-label
          class="bg-primary text-white shadow-2"
        >
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
          v-model="model"
          inline-label
          class="bg-primary text-white shadow-2"
        >
          <q-tab name="豪华轿车" label="豪华轿车" />
          <q-tab name="微型车" label="微型车" />
          <q-tab name="箱型车" label="箱型车" />
          <q-tab name="大巴车" label="大巴车" />
          <q-tab name="敞篷车" label="敞篷车" />
          <q-tab name="双门汽车" label="双门汽车" />
          <q-btn-dropdown auto-close stretch flat label="More...">
            <q-list>
              <q-item clickable @click="brand = '商务车'">
                <q-item-section>商务车</q-item-section>
              </q-item>
              <q-item clickable @click="brand = '搅拌车'">
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
          <q-tab name="under three" label="3万以下" />
          <q-tab name="p2" label="3-5万" />
          <q-tab name="p3" label="5-7万" />
          <q-tab name="p4" label="7-9万" />
          <q-tab name="p5" label="9-12万" />
          <q-tab name="p6" label="12-16万" />
          <q-btn-dropdown auto-close stretch flat label="More...">
            <q-list>
              <q-item clickable @click="price = 'p7'">
                <q-item-section>16-20万</q-item-section>
              </q-item>
              <q-item clickable @click="price = 'p8'">
                <q-item-section>20万以上</q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </q-tabs>
      </div>
    </div>
    <div style="width: 1%"/>
    <div class="q-pa-md row items-start q-gutter-md flex-center">
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/mountains.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/parallax2.jpg">
          <div class="absolute-bottom">
            <div class="text-h6">{{carInfo1.message}}</div>
            <div class="text-subtitle2">{{carInfo1.price}}</div>
          </div>
        </q-img>

        <q-card-actions>
          <q-btn flat>查看详情</q-btn>
        </q-card-actions>
      </q-card>
    </div>
    <div class="q-pa-lg flex flex-center">
      <q-pagination
        v-model="current"
        color="blue"
        :max="10"
        :max-pages="10"
        :boundary-numbers="false"
      />
    </div>
  </q-page>
</template>

<script>
export default {
  name: 'Search',
  data () {
    return {
      text: '',
      brand: '大众',
      model: '豪华轿车',
      price: 'p1',
      carInfo1: {
        message: '奔驰 2019款C200 ',
        price: '23.88万',
        img: 'car_details_test_img/1.png'
      },
      current: 1
    }
  }
}
</script>

<style lang="sass" scoped>
.my-card
  width: 100%
  max-width: 250px
</style>
