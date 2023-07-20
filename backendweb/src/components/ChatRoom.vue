<template>
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
    <div id="message_container">
      消息列表:{{ message_info }}
    </div>
  </div>
  
</template>

<script>
import { onMounted, onUnmounted ,ref} from "vue";
export default {
  setup() {
    let message_info = ref("");
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
        updateMessageInfo(message);
        console.log(message.data);
        
      };

      socket.onclose = () => {
        console.log("disconnected!");
      };
    });

    onUnmounted(() => {
      socket.close();
    });

    //发送消息给后端服务器
    const sendMessageToServer = (message) => {
      message = username +": "+ message;
      socket.send(message);
    };

    const updateMessageInfo = (message) =>{
      message_info.value += message.data + '\n';
    }

    return {
      sendMessageToServer,
      updateMessageInfo,
      message_info,
    };
  },
};
</script>

<style>
</style>