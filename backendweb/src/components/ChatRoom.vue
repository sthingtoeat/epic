<template>
  <div class="container">
    <div class="messageSendBox">
      <div class="row justify-content-md-center">
        <div class="col-6">
          <form @submit.prevent="sendMessageToServer(message)">
            <div class="mb-3">
              <label for="message" class="form-label">信息：</label>
              <input
                v-model="message"
                type="text"
                class="form-control"
                id="message"
                placeholder="输入信息.."
              />
            </div>
            <button type="submit" class="btn btn-success">发送</button>
          </form>
        </div>
      </div>
    </div>
      <div id="message_container">
        <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true" class="scrollspy-example bg-body-tertiary p-3 rounded-2" tabindex="0">
          <div v-for="message in messages.content" :key="message.id">
            <h4 id="scrollspyHeading1">{{message.username}}:</h4>
            <p>{{message.content}}</p>
          </div>
        </div>
    </div>

  </div>
</template>

<script>
import { onMounted, onUnmounted, reactive} from "vue";
export default {
  setup() {
    const messages = reactive({
      count:2,
      content:[
        {
          id:1,
          username:"[系统消息]",
          content:"欢迎来到米奇妙妙屋"
        },
      
      ]
    });

    let userId = localStorage.getItem("userId");
    let username = localStorage.getItem("username");
    let socket = null;
    const socketUrl = `ws://localhost:3000/websocket/${userId}`;

    onMounted(() => {
      socket = new WebSocket(socketUrl);

      socket.onopen = () => {
        console.log("connected!");
      };

      socket.onmessage = (message) => {
        updateMessages(message.data);
        console.log(message.data);
         
      };

      socket.onclose = () => {
        console.log("disconnected!");
      };
    });

    onUnmounted(() => {
      socket.close();
      localStorage.clear();
    });

    //发送消息给后端服务器
    const sendMessageToServer = (message) => {
      let data = username + "_" + message;
      socket.send(data);
    };

    //更新一下消息记录
    const updateMessages = (message) => {
      messages.count ++;
      messages.content.push({
        id:messages.count,
        username:message.split("_")[0],
        content:message.split("_")[1],
      })
    };

    return {
      sendMessageToServer,
      updateMessages,
      messages,
    };
  },
};
</script>

<style scoped>
div.container {
  width: 60%;
  height: 1000px;
  margin-top: 1%;
  color: white;
  background-color: black;
  background-attachment: fixed;
  opacity: 80%;
}
#message_container{
  width: 100%;
  height: 40%;
  margin-top: 2%;
  color: yellow;
  overflow:scroll;
}
</style>