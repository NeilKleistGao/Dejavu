<!--
 * @FileDescription: 个人用户信息页面
 * @Author: wnn
 * @Date: 2021/5/28
 * @LastEditors: wnn
 * @LastEditTime: 2021/6/14
 -->
<template>
  <q-page class="flex" style="margin-top: 2em; margin-bottom: 1em">
    <div style="min-height: 600px; width: 100%">
      <q-splitter
        v-model="splitterModel"
        style="height:100%"
      >
        <template v-slot:before>
          <q-tabs
            v-model="tab"
            vertical
            class="text-teal"
            active-color="teal"
            indicator-color="teal"
          >
            <q-tab name="UserInformation" icon="img:icons/gerenxinxi.png" label="用户信息" />
            <q-tab name="Bargain"  icon="img:icons/kanjiashangcheng.png" label="砍价信息" />
            <q-tab name="Transaction" icon="img:icons/jiaoyi.png" label="交易信息"/>
            <q-tab name="CarInfo"  icon="img:icons/cheliangxinxi.png" label="卖车信息" />
          </q-tabs>
        </template>

        <template v-slot:after>
          <q-tab-panels
            v-model="tab"
            animated
            swipeable
            vertical
            transition-prev="jump-up"
            transition-next="jump-up"
            style="width: auto"
          >
            <q-tab-panel name="UserInformation">
              <div class="text-h4 q-mb-md">个人信息</div>
              <template>
                <div class="q-pa-md" style="width: 40%">
                  <q-avatar size="128px" style="margin-bottom: 0.2em">
                    <img :src="user_data.avatar"/>
                    <q-popup-proxy>
                      <q-card style="min-width: 200px; min-height: 120px; margin-top: 0.5em">
                        <q-card-section>
                          <div class="text-subtitle1">上传头像</div>
                        </q-card-section>
                        <q-card-section>
                          <q-file label="选择文件" v-model="user_data.avatar_file" accept="image/*" outlined/>
                        </q-card-section>
                        <q-card-actions align="center">
                          <q-btn label="上传" @click="upload" color="primary" style="padding-left: 0.5em; padding-right: 0.5em"/>
                        </q-card-actions>
                      </q-card>
                    </q-popup-proxy>
                  </q-avatar>
                  <q-input label="电话号码" disable v-model="user_data.phone" style="margin-bottom: 0.5em"/>
                  <q-input label="用户名" v-model="user_data.name" style="margin-bottom: 0.5em"/>
                  <q-input label="邮箱" v-model="user_data.mail" style="margin-bottom: 0.5em"/>

                  <div>
                    <q-btn label="修改密码" color="primary" style="margin-right: 0.5em" @click="user_data.password_changing = true"/>
                    <q-btn label="保存修改" color="primary"/>
                  </div>
                </div>
              </template>
            </q-tab-panel>

            <q-tab-panel name="Bargain">
              <div class="text-h4 q-mb-md">砍价信息</div>
              <template>
                <div class="q-pa-md" style="width: 100%">
                  <q-table
                    title="砍价记录"
                    :data="data"
                    :columns="columns"
                    row-key="name"
                    hide-header
                    hide-bottom
                  />
                  <router-link>修改个人信息</router-link>
                </div>
              </template>
            </q-tab-panel>

            <q-tab-panel name="Transaction">
              <div class="text-h4 q-mb-md">交易记录</div>
              <div class="q-pa-md">
                <q-table
                  title="交易记录"
                  :data=trans_data
                  :columns="trans"
                  row-key="name"
                  hide-header
                  hide-bottom
                />
              </div>
            </q-tab-panel>
            <q-tab-panel name="CarInfo">
              <div class="text-h4 q-mb-md">卖车信息</div>
              <div class="q-pa-md">
                <q-table
                  title="卖车记录"
                  :data="sale_data"
                  :columns="sales"
                  row-key="name"
                  hide-header
                  hide-bottom
                  style="width: auto"
                />
              </div>
            </q-tab-panel>
          </q-tab-panels>
        </template>

      </q-splitter>
    </div>

    <q-dialog v-model="user_data.password_changing" persistent>
      <q-card style="min-width: 400px">
        <q-card-section>
          <div class="text-h4">修改密码</div>
        </q-card-section>
        <q-card-section>
          <q-input label="旧密码" v-model="user_data.old_password" type="password"/>
          <q-input label="新密码" v-model="user_data.new_password" type="password"/>
          <q-input label="确认密码" v-model="user_data.conform_password" type="password"/>
        </q-card-section>

        <q-card-actions>
          <q-btn label="确定" color="positive" @click="modifyPassword"/>
          <q-btn label="取消" @click="user_data.password_changing = false" color="negative"/>
        </q-card-actions>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>

export default {
  name: 'User',
  data () {
    return {
      tab: 'UserInformation',
      splitterModel: 20,
      user_data: {
        name: '',
        avatar: 'default-avatar.jpg',
        mail: '',
        phone: '',
        old_password: '',
        new_password: '',
        conform_password: '',
        password_changing: false,
        avatar_file: null
      },
      columns: [
        {
          name: 'desc',
          required: true,
          label: 'Dessert (100g serving)',
          align: 'left',
          field: row => row.name,
          format: val => `${val}`,
          sortable: true
        },
        { name: 'uid', align: 'center', label: 'uid', field: 'uid', sortable: true },
        { name: 'carID', label: 'carId', field: 'carId', sortable: true },
        { name: 'pirce', label: 'price', field: 'price' },
        { name: 'start_time', label: 'start_time', field: 'start_time' },
        { name: 'end_time', label: 'end_time', field: 'sodium' }
      ],
      data: [
        {
          name: 1,
          uid: 2017213391,
          carId: 101875436,
          price: '20.3w',
          start_time: '2020/04/20 14:00',
          end_time: '2020/04/20 14:35'
        },
        {
          name: 2,
          uid: 2017213391,
          carId: 101875436,
          price: '2.3w',
          start_time: '2020/05/13 16:17',
          end_time: '2020/05/13 16:31'
        },
        {
          name: 3,
          uid: 2017213391,
          carId: 101875436,
          price: '20.3w',
          start_time: '2020/04/20 14:00',
          end_time: '2020/04/20 14:35'
        }
      ],
      trans: [
        {
          name: 'desc',
          required: true,
          label: 'Dessert',
          align: 'left',
          field: row => row.name,
          format: val => `${val}`,
          sortable: true
        },
        { name: 'sale_id', align: 'center', lable: 'sale_id', field: 'sale_id' },
        { name: 'buyer_id', align: 'center', label: 'buyer_id', field: 'buyer_id' },
        { name: 'seller_id', align: 'center', label: 'seller_id', field: 'seller_id' },
        { name: 'car_id', align: 'center', label: 'car_id', field: 'car_id' },
        { name: 'deal_price', align: 'center', label: 'deal_price', field: 'deal_price', sortable: true },
        { name: 'deal_time', align: 'center', label: 'deal_time', field: 'deal_time', sortable: true }
      ],
      trans_data: [
        {
          name: 1,
          sale_id: 202104211605,
          buyer_id: 1018472973,
          seller_id: 1020472973,
          carId: 11111111,
          deal_price: 23,
          deal_time: '2020/04/21 16:05'
        },
        {
          name: 2,
          sale_id: 202104211605,
          buyer_id: 1018472973,
          seller_id: 1020472973,
          carId: 11111111,
          deal_price: 23,
          deal_time: '2020/04/21 16:05'
        }
      ],
      sales: [
        {
          name: 'sales',
          required: true,
          label: 'sales',
          align: 'left',
          field: row => row.name,
          format: val => `${val}`,
          sortable: true
        },
        { name: 'carID', label: 'carId', field: 'carId', sortable: true },
        { name: 'state', label: 'state', field: 'state', sortable: true }
      ],
      sale_data: [
        {
          name: 1,
          carID: 534234632,
          state: '未售出'
        },
        {
          name: 2,
          carID: 3457845786,
          state: '已售出'
        }
      ]
    }
  },
  methods: {
    upload () {
      const self = this
      const file = self.user_data.avatar_file
      const reader = new FileReader()
      reader.readAsDataURL(file)
      reader.onloadend = function (e) {
        const data = file
        console.log(data)
        const formData = new FormData()
        formData.append('smfile', data)
        formData.append('Authorization', '')
        self.$axios.post('/cdn/upload', formData, {
          headers: {
            Authorization: ''
          }
        }).then((response) => {
          if (response.status === 200 && response.data.success) {
            const url = response.data.data.url
            self.$axios.post('/api/user/info/modify', {
              uid: sessionStorage.getItem('uid'),
              token: sessionStorage.getItem('token'),
              avatar: url
            }, {
              headers: { token: sessionStorage.getItem('token') }
            }).then((response) => {
              console.log(response)
              if (response.status === 200 && response.data.result) {
                window.location.reload()
              }
            })
          }
        })
      }
    },
    modifyPassword () {
      if (this.user_data.new_password !== this.user_data.conform_password) {
        alert('两次密码不一致')
      } else {
        const self = this
        console.log(self.user_data.new_password)
        this.$axios.post('/api/user/info/password', {
          uid: sessionStorage.getItem('uid'),
          token: sessionStorage.getItem('token'),
          old: self.user_data.old_password,
          new: self.user_data.new_password
        }, {
          headers: { token: sessionStorage.getItem('token') }
        }).then((response) => {
          if (response.status === 200 && response.data.result) {
            self.user_data.password_changing = false
            alert('修改成功')
          } else {
            alert(response.data.info)
          }
        })
      }
    }
  },
  beforeMount () {
    const self = this
    this.$axios.get('/api/user/info/query?uid=' + sessionStorage.getItem('uid'), {
      headers: { token: sessionStorage.getItem('token') }
    }).then((response) => {
      if (response.status === 200) {
        const data = response.data
        self.user_data.name = data.name
        self.user_data.mail = data.mail
        self.user_data.phone = data.phone_number

        if (data.avatar !== null && data.avatar !== undefined && data.avatar !== '') {
          self.user_data.avatar = data.avatar
        }
      }
    })
  },
  beforeRouteEnter (to, from, next) {
    if (sessionStorage.getItem('token') === null) {
      window.location = '/#/login'
      window.location.reload()
    } else {
      next()
    }
  }
}
</script>

<style scoped>

</style>
