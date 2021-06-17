<!--
 * @FileDescription: 填写卖车信息，预约卖车
 * @Author: NeilKleistGao
 * @Date: 2021/4/19
 * @LastEditors: ZoeChou
 * @LastEditTime: 2021/6/15
 -->
<template>
  <q-page>
    <div class="row" id="sellValuateBox" style="height: 80vh">
      <div class="col-2"/>
      <div class="col-8">
        <div class="row" style="height: 30%"/>
        <div class="text-h3 text-center" style="margin-top: 0.5em; color: white">Dejevu二手车 二手车行业领军者</div>
        <div class="row" style="height: 40%">
          <div class="col-6" style="display:flex; justify-content: center; align-content: center; align-items: center">
            <div style="width: 50%; height: 25%">
              <q-btn @click="sellAppointment = true" color="primary" label="预约卖车" style="width: 100%; height: 100%; font-size: medium; font-weight: bold"/>
              <div>
                <q-dialog v-model="sellAppointment">
                  <sell-content/>
                </q-dialog>
              </div>
            </div>
          </div>
          <div class="col-6" style="display:flex; justify-content: center; align-content: center; align-items: center">
            <div style="width: 50%; height: 25%">
              <q-btn unelevated color="primary" label="估价" style="width: 100%; height: 100%; font-size: medium; font-weight: bold"/>
            </div>
          </div>
        </div>
        <div class="row" style="height: 20%"/>
      </div>
      <div class="col-2"/>
    </div>
    <div class="row" id="QABox">
      <div class="col-8" style="background-color: #EFEFEF">
        <div class="text-h5 text-center" style="margin-top: 0.5em">购车问答</div>
        <div style="margin: 3em 2em">
          <q-chat-message
            name="用户"
            :text="['卖车流程是怎样的？']"
            sent/>
          <q-chat-message
            name="Dejavu二手车"
            :text="['1、在本页填写卖车信息或拨打400-023-1529联系我们。',
            '2、由检测师进行车辆信息收集，并经过瓜子评估，给出瓜子价参考，由车主确定发布报价。',
            '3、达成交易合同，支付车款并过户。']"/>
          <q-chat-message
            name="用户"
            :text="['卖车需要准备什么？']"
            sent/>
          <q-chat-message
            name="Dejavu二手车"
            :text="['1、身份证；2、环保标；3、购置税本；4、行驶证；5、检字标；',
            '6、购置税发票；7、车辆登记证；8、交强险标；9、购车发票/最近一次过户发票；10、交强险单。']"/>
        </div>
      </div>
    </div>
  </q-page>
</template>

<script>
import SellContent from 'components/sellContent'
export default {
  name: 'Sell',
  components: { SellContent },
  data: function () {
    return {
      sellAppointment: false // 是否显示预约卖车控件
    }
  }
}
</script>

<style scoped>
  html, body, q-page {
    height: 100%;
  }
  #sellValuateBox {
    justify-content: center;
    align-items: stretch;
    background-image: url("../../public/sell-bg.jpg");
  }
  #QABox {
    justify-content: center;
    align-content: center;
    background-color: #EFEFEF;
  }
</style>
