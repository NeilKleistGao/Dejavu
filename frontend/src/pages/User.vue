<!--
 * @FileDescription: 个人用户信息页面
 * @Author: wnn
 * @Date: 2021/5/28
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/6/15
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
                    :data="bargain_data"
                    :columns="columns"
                    row-key="name"
                    hide-bottom
                    no-data-label="没有查询到相关数据"
                  >
                    <template v-slot:body="props">
                      <q-tr>
                        <q-td key="name" :props="props">
                          <a :href="'/#/car/' + props.row.car_id">{{ props.row.name }}</a>
                        </q-td>
                        <q-td key="price" :props="props">
                          {{ props.row.price }}
                        </q-td>
                        <q-td key="state" :props="props">
                          <q-badge v-if="props.row.state === -1" color="primary" label="等待车主联系"/>
                          <q-badge v-else-if="props.row.state === 0" color="positive" label="车主正在联系您"/>
                          <q-badge v-else color="negative" label="交易已结束"/>
                        </q-td>
                      </q-tr>
                    </template>
                  </q-table>
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
        { name: 'name', label: '车辆', field: 'name', sortable: true },
        { name: 'price', label: '价格', field: 'price', sortable: true },
        { name: 'state', label: '状态', field: 'state', sortable: true }
      ],
      bargain_data: [],
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
              userId: Number.parseInt(sessionStorage.getItem('uid')),
              token: sessionStorage.getItem('token'),
              avatar: url,
              mail: self.user_data.mail
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
          oldPassword: self.user_data.old_password,
          newPassword: self.user_data.new_password
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
    },
    getBargainList () {
      const id = sessionStorage.getItem('uid')
      const token = sessionStorage.getItem('token')
      this.$axios.post('/api/transaction/bargain/query', {
        uid: id
      }, {
        headers: {
          token: token
        }
      }).then((response) => {
        console.log(response.data)
        if (response.status === 200 && response.data.errCode === 0) {
          this.bargain_data = response.data.result
          const self = this
          console.log(this.bargain_data)
          for (let i = 0; i < this.bargain_data.length; ++i) {
            this.$axios.get('/api/car?id=' + this.bargain_data[i].car_id).then((response) => {
              if (response.status === 200) {
                self.bargain_data[i].name = response.data.manufacturer + response.data.model_name
              }
            })

            const start = Date.parse(this.bargain_data[i].start_time)
            const end = Date.parse(this.bargain_data[i].end_time)
            const now = new Date()
            now.setHours(0)
            now.setMinutes(0)
            now.setSeconds(0)

            if (start > now) {
              this.bargain_data[i].state = -1
            } else if (now > end) {
              this.bargain_data[i].state = 1
            } else {
              this.bargain_data[i].state = 0
            }
          }
        } else {
          sessionStorage.removeItem('uid')
          sessionStorage.removeItem('token')
          window.location = '/#/login'
          window.location.reload()
        }
      })
    },
    getTransactionList () {
      const id = sessionStorage.getItem('uid')
      const token = sessionStorage.getItem('token')
      this.$axios.get('/api/transaction/sale/query?uid=' + id + '&token=' + token + '&buyer=' + id + '&seller=' + id, {
        headers: {
          token: token
        }
      }).then((response) => {
        console.log(response)
        if (response.status === 200 && response.data.status === 200) {
          this.trans_data = response.data
        } else {
          sessionStorage.removeItem('uid')
          sessionStorage.removeItem('token')
          window.location = '/#/login'
          window.location.reload()
        }
      })
    }
  },
  beforeMount () {
    const self = this
    this.$axios.get('/api/user/info/query?uid=' + sessionStorage.getItem('uid'), {
      headers: { token: sessionStorage.getItem('token') }
    }).then((response) => {
      console.log(response)
      if (response.status === 200) {
        const data = response.data
        self.user_data.name = data.name
        self.user_data.mail = data.mail
        self.user_data.phone = data.phone_number

        if (data.avatar !== null && data.avatar !== undefined && data.avatar !== '') {
          self.user_data.avatar = data.avatar
        }
      } else {
        sessionStorage.removeItem('uid')
        sessionStorage.removeItem('token')
        window.location = '/#/login'
        window.location.reload()
      }
    })

    this.getBargainList()
    this.getTransactionList()
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
