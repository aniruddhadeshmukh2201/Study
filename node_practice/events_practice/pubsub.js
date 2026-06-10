class PubSub {
  constructor() {
    this.subscribers = {};
  }

  // Subscribe to a topic
  subscribe(topic, callback) {
    if (!this.subscribers[topic]) {
      this.subscribers[topic] = new Set();
    }
    this.subscribers[topic].add(callback);
    return () => {
      this.subscribers[topic].delete(callback);
    };
  }

  // Publish to a topic
  publish(topic, data) {
    if (!this.subscribers[topic]) return;

    for (const callback of this.subscribers[topic]) {
      try {
        callback(data);
      } catch (err) {
        console.error(`Error in subscriber callback for topic "${topic}":`, err);
      }
    }
  }

  // Optional: Unsubscribe all from a topic
  clear(topic) {
    if (this.subscribers[topic]) {
      delete this.subscribers[topic];
    }
  }
}


const pubsub = new PubSub();

// Subscriber 1
const unsubscribe1 = pubsub.subscribe('news', (data) => {
  console.log('[Subscriber 1] News received:', data);
});

// Subscriber 2
const unsubscribe2 = pubsub.subscribe('news', (data) => {
  console.log('[Subscriber 2] Breaking news:', data);
});

// Publish an event
pubsub.publish('news', { headline: 'Aliens land on Earth!' });

// Later, unsubscribe a listener
unsubscribe1();

// Publish again
pubsub.publish('news', { headline: 'Aliens now eating pizza!' });