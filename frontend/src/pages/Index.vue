<!--
 * @FileDescription: 主页文件
 * @Author: ZoeChou
 * @Date: 2021/4/19
 * @LastEditors: NeilKleistGao
 * @LastEditTime: 2021/5/12
 -->
<template>
  <q-page>
    <div style="height: 8vh;"></div>
    <q-form @submit="gotoSearch(null)">
      <div class="row" id="searchBox">
        <div class="col-7">
          <q-input v-model="message" outlined label="请输入关键字" />
        </div>
        <div class="col-1">
          <q-btn @click="gotoSearch(null)" type="submit" color="primary" label="搜索" class="fill" style="font-size: medium"/>
        </div>
      </div>
    </q-form>

    <div style="height: 5vh;"/>
    <div class="row" id="buySellBox">
      <div class="col-8">
        <q-card>
          <div class="row">
            <div style="width: 4vh"/>
            <div class="col-6">
              <div class="column" style="width: 100%; margin-bottom: 3em">
                <div style="height: 2vh"/>
                <div class="col">
                  <q-btn color="blue" icon-right="send" label="我要买车" @click="gotoSearch(null)" style="font-size: medium;"/>
                </div>
                <div style="height: 4vh"/>
                <div class="col" style="width: 100%">
                  <q-btn-group outline spread style="margin-bottom: 1em">
                    <q-btn size="md" outline color="primary" v-for="item in manufacturerGroup" :key="item.label" :label="item.label" @click="gotoSearch(item.value)"/>
                  </q-btn-group>

                  <q-btn-group outline spread style="margin-bottom: 1em">
                    <q-btn size="md" outline color="primary" v-for="item in priceRange" :key="item.label" :label="item.label" @click="gotoSearch(item.value)"/>
                  </q-btn-group>

                  <q-btn-group outline spread>
                    <q-btn size="md" outline color="primary" v-for="item in typeRange" :key="item.label" :label="item.label" @click="gotoSearch(item.value)"/>
                  </q-btn-group>
                </div>
              </div>
            </div>

            <div style="width: 8vh"/>
            <div class="col-4">
              <div class="column" style="width: 100%; height: 100%">
                <div style="height: 2vh"/>
                <div class="col-2">
                  <q-btn @click="gotoSell" color="blue" icon-right="send" label="我要卖车" style="font-size: medium;"/>
                </div>
                <div class="col-8">
                  <div class="row" style="height: 100%">
                    <div class="col-6" style="display:flex; align-content: center; align-items: center">
                      <div style="width: 65%; height: 28%">
                        <q-btn @click="sellAppointment = true" unelevated color="primary" label="预约卖车" style="width: 100%; height: 100%; font-size: medium; font-weight: bold"/>
                        <div>
                          <q-dialog v-model="sellAppointment">
                            <sell-content/>
                          </q-dialog>
                        </div>
                      </div>
                    </div>
                    <div class="col-6" style="display:flex; align-content: center; align-items: center">
                      <div style="width: 65%; height: 28%">
                        <q-btn unelevated color="primary" label="估价" style="width: 100%; height: 100%; font-size: medium; font-weight: bold"/>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </q-card>
      </div>
    </div>
    <div style="height: 5vh;"/>
    <div class="row" id="QABox">
      <div class="col-8" style="background-color: #EFEFEF">
        <div class="text-h4 text-center" style="margin-top: 0.5em">购车问答</div>
        <div style="margin: 3em 2em">
          <q-chat-message
            name="用户"
            :text="['为什么要选择Dejavu二手车？']"
            sent/>
          <q-chat-message
            name="Dejavu二手车"
            :text="['Dejavu二手车展示车源经过259项授权检测',
             '售出车源将依据合同约定享受平台售后保障','如发现售出车源是重大事故车、火烧车、水泡车、命案车，30天内可退']"/>
          <q-chat-message
            name="用户"
            :text="['Dejavu二手车的车辆异地可以看车吗？']"
            sent/>
          <q-chat-message
            name="Dejavu二手车"
            :text="['可以，不过请您在异地看车之前务必先和我们的销售取得联系，确认车辆的在售情况，以免您异地看车时出现车辆已售的情况耽误您的时间。']"/>
          <q-chat-message
            name="用户"
            :text="['关于交易安全及款项支付有什么需要注意的吗？']"
            sent/>
          <q-chat-message
            name="Dejavu二手车"
            :text="['为保障交易安全，在Dejavu二手车进行交易必须签署正式的电子交易合同。交易合同签署过程中，请您务必提供所需交易资料和信息，仔细观看签约告知视频，严格进行人脸识别，认真阅读合同后确认签署。',
             'Dejavu二手车禁止任何Dejavu工作人员以现金、个人微信、个人支付宝、个人银行账户刷卡或转账等方式直接收取您任何款项（包括但不限于服务费、意向金、车辆价款等）。',
              'Dejavu二手车显著提示：交易过程中所涉及的所有款项，您均应通过Dejavu二手车提供的官方收款渠道（官方微信、官方支付宝、官方POS刷卡或对公账户转账）进行支付。',
               '如您发现Dejavu工作人员违规操作，您应拒绝并及时向Dejavu二手车进行投诉举报，否则您须自行承担由此造成的任何损失。']"/>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import SellContent from 'components/sellContent'
export default {
  name: 'PageIndex',
  components: { SellContent },
  data () {
    return {
      message: '', // 搜索信息
      sellAppointment: false, // 是否点击预约买车按钮
      manufacturerGroup: [ // 生产商信息列表
        { label: '大众', value: { manufacturer: '大众' } },
        { label: '宝马', value: { manufacturer: '宝马' } },
        { label: '奔驰', value: { manufacturer: '奔驰' } },
        { label: '丰田', value: { manufacturer: '丰田' } },
        { label: '本田', value: { manufacturer: '本田' } },
        { label: '奥迪', value: { manufacturer: '奥迪' } },
        { label: '别克', value: { manufacturer: '别克' } }
      ],
      priceRange: [ // 价格区间信息
        { label: '3万以下', value: { 'min-guild-price': 0, 'max-guild-price': 3 } },
        { label: '3-5万', value: { 'min-guild-price': 3, 'max-guild-price': 5 } },
        { label: '5-7万', value: { 'min-guild-price': 5, 'max-guild-price': 7 } },
        { label: '7-9万', value: { 'min-guild-price': 7, 'max-guild-price': 9 } },
        { label: '9-12万', value: { 'min-guild-price': 9, 'max-guild-price': 12 } },
        { label: '12-16万', value: { 'min-guild-price': 12, 'max-guild-price': 16 } },
        { label: '16-20万', value: { 'min-guild-price': 16, 'max-guild-price': 20 } }
      ],
      typeRange: [ // 类型信息
        { label: '微型车', value: { body: '微型车' } },
        { label: '厢型车', value: { body: '厢型车' } },
        { label: '大巴车', value: { body: '大巴车' } },
        { label: '敞篷车', value: { body: '敞篷车' } },
        { label: '双门车', value: { body: '双门汽车' } },
        { label: '商务车', value: { body: '商务车' } },
        { label: '搅拌车', value: { body: '搅拌车' } }
      ]
    }
  },

  methods: {
    gotoSearch (data) {
      if (data === null) {
        this.$router.push({
          path: '/search'
        })
      } else {
        this.$router.push({
          path: '/search',
          query: data
        })
      }
    },
    gotoSell () {
      this.$router.push({
        path: '/sell'
      })
    }
  }
}
</script>

<style scoped>
  html, body, q-page {
    height: 100%;
  }
  #searchBox {
    justify-content: center;
    align-content: center;
    align-items: stretch;
  }
  #buySellBox {
    justify-content: center;
    align-items: stretch;
  }
  #QABox {
    justify-content: center;
    align-content: center;
    background-color: #EFEFEF;
  }
  .fill {
    width: 100%;
    height: 100%;
  }
</style>
