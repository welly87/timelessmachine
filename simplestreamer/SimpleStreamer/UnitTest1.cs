using Coe.WebSocketWrapper;
using System;
using System.Net.WebSockets;
using System.Threading;
using System.Threading.Tasks;
using Xunit;

namespace SimpleStreamer
{
    public class SimpleStreamer
    {
        [Fact]
        public void Start()
        {
            var socket = WebSocketWrapper
                .Create("ws://localhost:8080/etp")
                .OnConnect(x =>
                {
                    Console.WriteLine();
                })
                .OnMessage( (byte[] x, WebSocketWrapper y) =>
                {
                    Console.WriteLine("");
                }).Connect();

            for (int i = 0; i < 10000; i++)
            {
                socket.SendMessage(new byte[10]);
            }

            socket.Disconnect();
        }

        [Fact]
        public async Task Should_connect_and_stream_data()
        {
            var ws = new ClientWebSocket();

            await ws.ConnectAsync(new Uri("ws://localhost:8080/etp"), CancellationToken.None);

            for (int i = 0; i < 1000; i++)
            {
                await ws.SendAsync(new ArraySegment<byte>(new byte[10]), WebSocketMessageType.Binary, true, CancellationToken.None);
            }
            
            
        }
    }
}
